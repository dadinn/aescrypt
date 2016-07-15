(ns aescrypt.core-test
  (:require [clojure.test :refer :all]
            [aescrypt.core :refer :all]))

(deftest encrypt-decrypt-test
  (testing "With freshly generated secret key and initialization vector,"
    (let [key (genkey 256)
          iv (geniv 16)]
      (testing "encryption/decryption of input works reliably"
        (let [input "SOME_PRIVATE_TEXT"
              encrypted (encrypt key iv input)]
          (is (= input (decrypt key iv encrypted))))))))
