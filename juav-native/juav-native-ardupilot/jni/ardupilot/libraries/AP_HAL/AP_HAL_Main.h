/*
 * Copyright (C) 2016  Intel Corporation. All rights reserved.
 *
 * This file is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This file is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
#pragma once

#include "HAL.h"

#ifndef AP_MAIN
#define AP_MAIN main
#endif

#define AP_HAL_MAIN() \
    AP_HAL::HAL::FunCallbacks callbacks(setup, loop); \
    extern "C" {                               \
    int AP_MAIN(int argc, char* const argv[]); \
    int AP_MAIN(int argc, char* const argv[]) { \
        hal.run(argc, argv, &callbacks); \
        return 0; \
    } \
    }

#define AP_HAL_MAIN_CALLBACKS(CALLBACKS) extern "C" { \
    int AP_MAIN(int argc, char* const argv[]); \
    int AP_MAIN(int argc, char* const argv[]) { \
        hal.run(argc, argv, CALLBACKS); \
        return 0; \
    } \
    }

/*#define AP_HAL_MAIN_JB_CALLBACKS(CALLBACKS) extern "C" { \
    int AP_MAIN(int argc, char** argv); \
        \
    void * helper_jb( void * ) { \
        char * argv[] = {"/home/adamczer/code/ardupilot/build/sitl/bin/arducopter","-A","udp:127.0.0.1:6001","-B","/dev/ttyS0","-C","/dev/ttyUSB0","-l","/home/erle/APM/logs","-t","/home/erle/APM/terrain/"};  \
        hal.run(11, argv, &copter); \
        return NULL; \
    } \
    int AP_MAIN(int argc, char** argv) { \
        printf("1\n"); \
	pthread_t ptid; \
	pthread_t ptid2; \
        pthread_create(&ptid2, NULL, helper_jb, NULL); \
        printf("2\n"); \
	pthread_create(&ptid, NULL, dumbyAStar, NULL); \
	pthread_join(ptid2, NULL); \
        printf("3\n"); \
	kill_dumby_work = 1; \
        return 0; \
    } */
//    }