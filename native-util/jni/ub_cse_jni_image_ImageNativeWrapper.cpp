/*
 * A simple libpng example program
 * http://zarb.org/~gc/html/libpng.html
 *
 * pulled content from https://gist.github.com/niw/5963798
 *
 * Modified by Yoshimasa Niwa to make it much simpler
 * and support all defined color_type.
 *
 * To build, use the next instruction on OS X.
 * $ brew install libpng
 * $ clang -lz -lpng16 libpng_test.c
 *
 * Copyright 2002-2010 Guillaume Cottenceau.
 *
 * This software may be freely redistributed under the terms
 * of the X11 license.
 *
 */
#include <jni.h>
#include <ub_cse_jni_image_ImageNativeWrapper.h>
#include <stdlib.h>
#include <stdio.h>
#include <png.h>

int width, height;
png_byte color_type;
png_byte bit_depth;
png_bytep *row_pointers = NULL;

JNIEXPORT jboolean JNICALL Java_ub_cse_jni_image_ImageNativeWrapper_loadImageFile
  (JNIEnv * env , jobject thisObj, jstring filename) {
          const char *nativeString = env->GetStringUTFChars(filename, 0);
          FILE *fp = fopen(nativeString, "rb");
          env->ReleaseStringUTFChars(filename, nativeString);

          png_structp png = png_create_read_struct(PNG_LIBPNG_VER_STRING, NULL, NULL, NULL);
          if(!png) abort();

          png_infop info = png_create_info_struct(png);
          if(!info) abort();

          if(setjmp(png_jmpbuf(png))) abort();

          png_init_io(png, fp);

          png_read_info(png, info);

          width      = png_get_image_width(png, info);
          height     = png_get_image_height(png, info);
          color_type = png_get_color_type(png, info);
          bit_depth  = png_get_bit_depth(png, info);

          // Read any color_type into 8bit depth, RGBA format.
          // See http://www.libpng.org/pub/png/libpng-manual.txt

          if(bit_depth == 16)
            png_set_strip_16(png);

          if(color_type == PNG_COLOR_TYPE_PALETTE)
            png_set_palette_to_rgb(png);

          // PNG_COLOR_TYPE_GRAY_ALPHA is always 8 or 16bit depth.
          if(color_type == PNG_COLOR_TYPE_GRAY && bit_depth < 8)
            png_set_expand_gray_1_2_4_to_8(png);

          if(png_get_valid(png, info, PNG_INFO_tRNS))
            png_set_tRNS_to_alpha(png);

          // These color_type don't have an alpha channel then fill it with 0xff.
          if(color_type == PNG_COLOR_TYPE_RGB ||
             color_type == PNG_COLOR_TYPE_GRAY ||
             color_type == PNG_COLOR_TYPE_PALETTE)
            png_set_filler(png, 0xFF, PNG_FILLER_AFTER);

          if(color_type == PNG_COLOR_TYPE_GRAY ||
             color_type == PNG_COLOR_TYPE_GRAY_ALPHA)
            png_set_gray_to_rgb(png);

          png_read_update_info(png, info);

          if (row_pointers)
            free(row_pointers);

          row_pointers = (png_bytep*)malloc(sizeof(png_bytep) * height);
          for(int y = 0; y < height; y++) {
            row_pointers[y] = (png_byte*)malloc(png_get_rowbytes(png,info));
          }

          png_read_image(png, row_pointers);

          fclose(fp);

          png_destroy_read_struct(&png, &info, NULL);
          return true;
  }

JNIEXPORT jint JNICALL Java_ub_cse_jni_image_ImageNativeWrapper_readPixelRed
  (JNIEnv * env, jobject thisObj, jint w, jint h) {
    png_bytep row = row_pointers[h];
    png_bytep px = &(row[w * 4]);
    return px[0];
  }

JNIEXPORT jint JNICALL Java_ub_cse_jni_image_ImageNativeWrapper_readPixelGreen
  (JNIEnv * env, jobject thisObj, jint w, jint h) {
    png_bytep row = row_pointers[h];
    png_bytep px = &(row[w * 4]);
    return px[1];
  }

JNIEXPORT jint JNICALL Java_ub_cse_jni_image_ImageNativeWrapper_readPixelBlue
  (JNIEnv * env, jobject thisObj, jint w, jint h) {
    png_bytep row = row_pointers[h];
    png_bytep px = &(row[w * 4]);
    return px[2];
  }

JNIEXPORT jint JNICALL Java_ub_cse_jni_image_ImageNativeWrapper_pullWidth
  (JNIEnv * env, jobject thisObj) {
    return width;
  }


JNIEXPORT jint JNICALL Java_ub_cse_jni_image_ImageNativeWrapper_pullHeight
  (JNIEnv * env, jobject thisObj) {
    return height;
  }

