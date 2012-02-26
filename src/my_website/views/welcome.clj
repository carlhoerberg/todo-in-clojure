(ns my-website.views.welcome
  (:require [my-website.views.common :as common])
  (:use [noir.core]
        [hiccup.core]
        [hiccup.page-helpers]
        [hiccup.form-helpers]))

(defpartial email-fields [{:keys [email]}]
            (label "email" "Email: ")
            (text-field "email" email))

(defpage "/" [:as email] 
         (common/site-layout
           (form-to [:post "/"]
                     (email-fields email)
                     (submit-button "Save"))))

(defpage [:post "/"] {:keys [email]}
         (str "Your email is " email ))

(defpage "/welcome" []
         (common/layout
           [:p "Welcome to my-website"]))

(defpage "/my-page" []
         (common/site-layout
           [:h1 "Welcome to my site!"]
           [:p "Hope you like it."]))

