(ns videoindex.cloudinary
  (:require [environ.core :refer [env]]
            [clojure.data.json :as json]
            [clj-http.client :as http]))

(def url (str "https://api.cloudinary.com/v1_1/" (env :cloudinary-cloud-name) "/resources/video"))

(defn- get-videos-data [url]
  (http/get url {:basic-auth [(env :cloudinary-api-key) (env :cloudinary-api-secret)]}))

(defn- parse-video-data [video]
  (let [id (:public_id video)
        parts (re-find #"^([0-9]{4})([0-9]{2})([0-9]{2})\.(.*)_[a-z0-9]*$" id)
        date (str (get parts 3) "." (get parts 2) "." (get parts 1))
        title (clojure.string/replace (get parts 4) "_" " ")]
    {:id id
     :date date
     :title title}))

(defn get-videos []
  (let [videos (-> url get-videos-data :body (json/read-json true) :resources)]
    (->> videos
      (map parse-video-data)
      (sort #(compare (:id %1) (:id %2))))))
