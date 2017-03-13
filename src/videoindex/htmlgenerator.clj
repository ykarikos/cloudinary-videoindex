(ns videoindex.htmlgenerator
  (:require [environ.core :refer [env]]
            [hiccup.core :as h]))

(def url-prefix (env :cloudinary-url-prefix))
(def video-formats
  [{:type "video/webm"
    :ext ".webm"}
   {:type "video/mp4"
    :ext ".mp4"}
    {:type "video/ogg"
     :ext ".wgv"}])

(defn- video-to-item
  [{:keys [id date title]}]
  (h/html
    [:li
      [:div
        [:video {:controls true
                 :poster (str url-prefix id ".jpg")
                 :preload "none"}
          (for [format video-formats]
            [:source {:src (str url-prefix id (:ext format))
                      :type (:type format)}])]]
      [:div date]
      [:div title]]))

(defn- get-year [video]
  (-> video :id (subs 0 4)))

(defn- generate-body [videos years]
  (h/html
    (for [year years]
      (list
        [:h1 year]
        [:ul
          (map video-to-item (filter #(= year (get-year %1)) videos))]))))

(defn generate-page [videos]
  (let [years (->> videos (map get-year) distinct)]
    (str (slurp "resources/frontmatter.html")
      (->> (generate-body videos years)
        (apply str))
      (slurp "resources/backmatter.html"))))
