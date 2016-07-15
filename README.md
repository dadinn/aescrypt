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

## License

Copyright © 2016 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
