BEGIN {
   graph_t newg;
   node_t  n;
}
BEG_G {
   newg = clone(NULL, $G);
}
N {
   if (substr(label, 0, 3 ) == "lib") {
     n = clone(newg, $);
     delete(newg, n);
   }
}
END_G {
   $O = newg;
}
