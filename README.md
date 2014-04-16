fm-index
========

FM Indexing and Backwards Search

An FM-Index is a compressed full-text substring index that is used to efficiently find the number of occurrences of a pattern inside the compressed text as well as the locations of the pattern(s) The motivation behind this data structure and supporting algorithms is the need to search large strings such as DNA in order to find particular genes which are composed of nucleotide sequences. These sequences act as the search query and the entire DNA sequence is the search space. This space is often times very large and the storage space can be reduced by compressing the string. A technique known as Burrows-Wheeler Transformation is executed on a string of characters to produce the Burrows-Wheeler text (BW text). The original text can be reconstructed from the BW text which allows us to discard the original, and much larger string providing compression. The BW text is further compressed using a wavelet tree which will also provide O(1) time computation of a rank queries.
