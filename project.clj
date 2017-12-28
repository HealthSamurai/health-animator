(defproject health-animator "0.0.2"
  :description "CLJS ReactNative Animations"
  :url "https://github.com/HealthSamurai/health-animator"
  :license {:name "MIT"
            :url  "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha16"]
                 [org.clojure/clojurescript "1.9.542"]
                 [reagent "0.7.0" :exclusions [cljsjs/react
                                               cljsjs/react-dom
                                               cljsjs/react-dom-server
                                               cljsjs/create-react-class]]]
  :signing {:gpg-key "mikle.sol@gmail.com"}
  :source-paths ["src"])
