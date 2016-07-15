# aescrypt

This is a Clojure library demonstrating the usage of AES encryption
using the Java crypto API, and it provides a easy to use Command line
interface for the basic operations, including generating base64
encoded secure AES keys, and initialization vectors, and
encrypting/decrypting content using those as parameters.

## Usage

The project can be built, packaged, and executed as command line tool
using [Leiningen](http://leiningen.org/) like the following:

	secret=`lein run genkey`
	echo $secret
	iv=`lein run geniv`
	echo $iv
	encrypted=`lein run encrypt $secret $iv "SOME_PRIVATE_TEXT"`
	echo $encrypted
	lein run decrypt $secret $iv $encrypted

It has to be noted that the command line interface defaults the secret
key sizes to 128 bits... this can be changed by adding key sizes like
256 after the genkey command, but it requires the
[Unlimited Strength JCE](http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html)
jars to be installed on the host.

## License

Copyright © 2016

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
