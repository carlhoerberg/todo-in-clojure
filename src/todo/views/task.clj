(ns todo.views.task
  (:use [noir.core]
        [korma.db]
        [korma.core]
        [hiccup.core]
        [hiccup.page-helpers]
        [hiccup.form-helpers])
  (:require [noir.response :as resp]
            [todo.views.common :as common]))

(defdb pg (postgres {:db "mywebsite"}))
(defentity task)

(defpartial todo-fields [{:keys [title done]}]
            (label "title" "Todo: ")
            (text-field "title" title))

(defn all-tasks []
  (select task
          (fields :id :title :done)))

(defpartial task-item [{:keys [title]}]
            [:li title])

(defpage "/" [:as todo] 
         (common/layout
           (form-to [:post "/"]
                    (todo-fields todo)
                    (submit-button "Save"))
           [:ul
            (map task-item (all-tasks))]))

(defpage [:post "/"] {:keys [title]}
         (insert task
                 (values {:title title}))
         (resp/redirect "/"))

