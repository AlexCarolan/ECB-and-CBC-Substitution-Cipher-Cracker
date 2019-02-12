# ECB-and-CBC-Substitution-Cipher-Cracker
When provided with known plain & cipher text, produces the correct key if an ECB or CBC substitution cipher was used (assuming block size of 1). It then uses the key to decode a provided cipher text file.
`Runs using the following input params <mode> <cipher1> <plain1> <cipher2> <output> <IV1>* <IV2>*`
  
  *IV1 and IV2 are only required for CBC mode and can be ommited when using ECB*
