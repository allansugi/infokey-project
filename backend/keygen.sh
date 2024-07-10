#!/bin/bash

key_file="./src/main/resources/keypair.pem"
pub_key_file="./src/main/resources/public.pem"
private_key_file="./src/main/resources/private.pem"

openssl genrsa -out $key_file 2048
openssl rsa -in $key_file -pubout -out $pub_key_file
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in $key_file -out $private_key_file