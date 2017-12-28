(ns example.core
    (:require [reagent.core :as r :refer [atom]]
              [re-frame.core :refer [subscribe dispatch dispatch-sync]]
              [health-animator.core :as animator]
              [example.handlers]
              [example.subs]))

(def ReactNative (js/require "react-native"))

(def app-registry (.-AppRegistry ReactNative))
(def text (r/adapt-react-class (.-Text ReactNative)))
(def view (r/adapt-react-class (.-View ReactNative)))
(def image (r/adapt-react-class (.-Image ReactNative)))
(def touchable-highlight (r/adapt-react-class (.-TouchableHighlight ReactNative)))

(def dimensions (.-Dimensions ReactNative))
(def window (js->clj (.get dimensions "window") :keywordize-keys true))

(defn app-root []
  (let [greeting (subscribe [:get-greeting])
        animate-view1 (r/atom false)
        animate-view2 (r/atom false)
        animate-view3 (r/atom false)
        animate-view4 (r/atom false)
        animate-view5 (r/atom false)]
    (fn []
      [view {:style {:flex-direction "column" :margin 40 :align-items "center"}}
[animator/animate {:style {:margin-bottom 20}
                          :fn #(reset! animate-view1 true)
                          :type :fade-in}
        [text {:style {:color "black"
                       :text-align "center"}}
         "fade-in"]]
       (when @animate-view1
         [animator/animate {:style {:margin-bottom 20}
                            :fn #(reset! animate-view2 true)
                            :type :fade-out}
          [text {:style {:color "black"
                         :text-align "center"}}
           "fade-out"]])
       (when @animate-view2
         [animator/animate {:style {:margin-bottom 20
                                    :width (:width window)
                                    :background-color "#999"}
                            :fn #(reset! animate-view3 true)
                            :type :left
                            :from-value (:width window)
                            :to-value 0}
          [text {:style {:color "black"
                         :text-align "center"}}
           "left->right"]])
       (when @animate-view3
         [animator/animate {:style {:margin-bottom 20
                                    :width (:width window)
                                    :background-color "#999"}
                            :fn #(reset! animate-view4 true)
                            :type :right
                            :from-value (:width window)
                            :to-value 0}
          [text {:style {:color "black"
                         :text-align "center"}}
           "right->left"]])
       (when @animate-view4
         [animator/animate {:style {:margin-bottom 20
                                    :width (:width window)
                                    :height 17
                                    :background-color "#999"}
                            :fn #(reset! animate-view5 true)
                            :type :top
                            :from-value (* (:height window) -1)
                            :to-value 0}
          [text {:style {:color "black"
                         :text-align "center"}}
           "top->bottom"]])
       (when @animate-view5
         [animator/animate {:style {:width (:width window)
                                    :height 17
                                    :background-color "#999"}
                            :type :bottom
                            :from-value (:height window)
                            :to-value 0}
          [text {:style {:color "black"
                         :text-align "center"}}
           "bottom->top"]])])))

(defn init []
  (dispatch-sync [:initialize-db])
  (.registerComponent app-registry "main" #(r/reactify-component app-root)))
