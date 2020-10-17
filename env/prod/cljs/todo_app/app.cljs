(ns todo-app.app
  (:require [todo-app.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
