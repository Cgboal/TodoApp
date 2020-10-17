(ns todo-app.db.core
    (:require
      [monger.core :as mg]
      [monger.collection :as mc]
      [monger.operators :refer :all]
      [mount.core :refer [defstate]]
      [todo-app.config :refer [env]]))

(defstate db*
  :start (-> env :database-url mg/connect-via-uri)
  :stop (-> db* :conn mg/disconnect))

(defstate db
  :start (:db db*))

(defn create-user [user]
  (mc/insert db "users" user))

(defn update-user [id first-name last-name email]
  (mc/update db "users" {:_id id}
             {$set {:first_name first-name
                    :last_name last-name
                    :email email}}))

(defn get-user [id]
  (mc/find-one-as-map db "users" {:_id id}))

(defn create-todo
  [todo]
  (mc/insert db "todos" todo))

(defn list-todos
  []
  (mc/find-maps db "todos" {}))

(defn update-todo [id title content]
  (mc/update db "todos" {:_id id}
             {$set {:title title
                    :content content}}))
