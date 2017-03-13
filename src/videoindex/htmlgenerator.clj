(ns videoindex.htmlgenerator
  (:require [videoindex.cloudinary :as cloudinary]
            [environ.core :refer [env]]
            [hiccup.core :as h]))

(def url-prefix (env :cloudinary-url-prefix))
(def video-formats
  [{:type "video/webm"
    :ext ".webm"}
   {:type "video/mp4"
    :ext ".mp4"}
    {:type "video/ogg"
     :ext ".wgv"}])

(defn video-to-item
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
      [:div title]
      [:div date]]))
