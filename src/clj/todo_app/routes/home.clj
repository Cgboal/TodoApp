(ns todo-app.routes.home
  (:require
   [todo-app.layout :as layout]
   [clojure.java.io :as io]
   [todo-app.middleware :as middleware]
   [todo-app.db.core :as db]
   [ring.util.response]
   [ring.util.http-response :as response]))

(defn home-page [request]
  (layout/render request "home.html"))

(defn create-todo [request]
  (db/create-todo (:body-params request))
  {:status 201})


(defn list-todos [request]
  {
   :status 200
   :body (db/list-todos)
   })

(defn home-routes []
  [""
   {:middleware [
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/docs" {:get (fn [_]
                    (-> (response/ok (-> "docs/docs.md" io/resource slurp))
                        (response/header "Content-Type" "text/plain; charset=utf-8")))}]
   ["/todos" {:get list-todos
              :post create-todo}]])

