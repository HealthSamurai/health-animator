# Health Animator
![](https://clojars.org/health-animator/latest-version.svg)

ClojureScript React Native animations

## Documentation
### Fade-in
Props:
```clojure
{:type :fade-in
 :duration 1000}
```

### Fade-out
Props:
```clojure
{:type :fade-out
 :duration 1000}
```

### Left to Right
```clojure
{:type :left
 :duration 1000
 :from-value 100
 :to-value 0}
```

### Right to Left
```clojure
{:type :right
 :duration 1000
 :from-value 100
 :to-value 0}
```

### Top to Bottom
```clojure
{:type :top
 :duration 1000
 :from-value -100
 :to-value 0}
```

### Bottom to Top
```clojure
{:type :bottom
 :duration 1000
 :from-value 100
 :to-value 0}
```

## Usage
```clojure
(:require [health-animator.core :as animator])

[animator/animate {:fn #(println "animation end") :type :fade-in :duration 3000}]
```

## License

Copyright © 2017 Health Samurai

Distributed under the MIT license.
