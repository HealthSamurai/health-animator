(ns health-animator.core
  (:require [reagent.core :as r]))

(def ReactNative (js/require "react-native"))
(def text (r/adapt-react-class (.-Text ReactNative)))
(def Easing (.-Easing ReactNative))
(def linear (.-linear Easing))
(def Animated (.-Animated ReactNative))
(def animated-timing (.-timing Animated))
(def animated-value (.-Value Animated))
(def animated-view (r/adapt-react-class (.-View Animated)))

(defn view [props childs]
  [animated-view props childs])

(defn fade-in [{:keys [duration fnc]} props childs]
  (let [value (r/atom (animated-value. 0))
        animated (animated-timing @value #js{:toValue 1
                                             :duration duration})]
    (.start animated #(when fnc (fnc)))
    (fn []
      [view (merge-with merge props {:style {:opacity @value}}) childs])))

(defn fade-out [{:keys [duration fnc]} props childs]
  (let [value (r/atom (animated-value. 1))
        animated (animated-timing @value #js{:toValue 0
                                             :duration duration})]
    (.start animated #(when fnc (fnc)))
    (fn []
      [view (merge-with merge props {:style {:opacity @value}}) childs])))

(defn from-to [{:keys [from from-value to-value duration fnc]} props childs]
  (let [value (r/atom (animated-value. from-value))
        animated (animated-timing @value #js{:toValue to-value
                                             :duration duration
                                             :easing linear})
        key (case from
              :left :marginLeft
              :right :marginRight
              :top :marginTop
              :bottom :marginTop)]
    (.start animated #(when fnc (fnc)))
    (fn []
      [view (merge-with merge props {:style {key @value}}) childs])))

(defn animate [props childs]
  (let [type (:type props)
        options {:duration (if (:duration props) (:duration props) 1000)
                 :fnc (:fn props)
                 :from (if (:from props) (:from props) :left)
                 :from-value (if (:from-value props) (:from-value props) 0)
                 :to-value (if (:to-value props) (:to-value props) 1)}
        props (dissoc props ["type" "duration" "fromValue" "toValue" "from" "fn"])]
    (fn []
      (case type
        :fade-in [fade-in options props childs]
        :fade-out [fade-out options props childs]
        :from-to [from-to options props childs]
        :left [from-to (merge options {:from :left}) props childs]
        :right [from-to (merge options {:from :right}) props childs]
        :top [from-to (merge options {:from :top}) props childs]
        :bottom [from-to (merge options {:from :bottom}) props childs]
        [text "Animation not found"]))))


