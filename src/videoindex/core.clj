(ns videoindex.core
  (:gen-class)
  (:require [videoindex.cloudinary :as cloudinary]
            [videoindex.htmlgenerator :as html]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (-> (cloudinary/get-videos)
    (html/generate-page)
    println))
