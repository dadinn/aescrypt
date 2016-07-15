(ns aescrypt.core
  (:import
   [org.apache.commons.codec.binary Base64]
   [java.security SecureRandom]
   [javax.crypto KeyGenerator Cipher SecretKey]
   [javax.crypto.spec SecretKeySpec IvParameterSpec])
  (:gen-class))

(defn encrypt [^String secret ^String initvec ^String content]
  (let [iv (IvParameterSpec. (Base64/decodeBase64 initvec))
        keyspec (SecretKeySpec. (Base64/decodeBase64 secret) "AES")
        c (Cipher/getInstance "AES/CBC/PKCS5Padding")]
    (.init c Cipher/ENCRYPT_MODE keyspec iv)
    (String. (Base64/encodeBase64 (.doFinal c (.getBytes content))))))

(defn decrypt [^String secret ^String initvec ^String content]
  (let [iv (IvParameterSpec. (Base64/decodeBase64 initvec))
        keyspec (SecretKeySpec. (Base64/decodeBase64 secret) "AES")
        c (Cipher/getInstance "AES/CBC/PKCS5Padding")]
    (.init c Cipher/DECRYPT_MODE keyspec iv)
    (String. (.doFinal c (Base64/decodeBase64 content)))))

(defn genkey [size]
  "Generates secret Base64 encoded AES key bytes"
  (let [kgen
        (doto (KeyGenerator/getInstance "AES")
          (.init size))
        kspec (.generateKey kgen)]
    (String. (Base64/encodeBase64 (.getEncoded kspec)))))

(defn geniv [size]
  "Securely generates random bytes for an AES initialization vector."
  (let [sr (SecureRandom.)
        ba (byte-array size)]
    (.nextBytes sr ba)
    (String. (Base64/encodeBase64 ba))))

(defn -main [& args]
  (println
    (case (first args)
    "encrypt"
    (let [[key iv content] (rest args)]
      (encrypt key iv content))
    "decrypt"
    (let [[key iv content] (rest args)]
      (decrypt key iv content))
    "genkey"
    (genkey 256) 
    "geniv"
    (geniv 16)
    "Usage: encrypt, decrypt, genkey, geniv.")))
