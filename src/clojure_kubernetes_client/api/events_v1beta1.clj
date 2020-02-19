(ns clojure-kubernetes-client.api.events-v1beta1
  (:require [clojure-kubernetes-client.core :refer [call-api check-required-params with-collection-format *api-context*]]
            [clojure.spec.alpha :as s]
            [spec-tools.core :as st]
            [orchestra.core :refer [defn-spec]]

            [clojure-kubernetes-client.specs.v1beta1-event :refer :all]

            [clojure-kubernetes-client.specs.v1-status :refer :all]

            [clojure-kubernetes-client.specs.v1-status :refer :all]

            [clojure-kubernetes-client.specs.v1-api-resource-list :refer :all]

            [clojure-kubernetes-client.specs.v1beta1-event-list :refer :all]

            [clojure-kubernetes-client.specs.v1beta1-event-list :refer :all]

            [clojure-kubernetes-client.specs.v1beta1-event :refer :all]

            [clojure-kubernetes-client.specs.v1beta1-event :refer :all]

            [clojure-kubernetes-client.specs.v1beta1-event :refer :all]
)
  (:import (java.io File)))


(defn-spec create-namespaced-event-with-http-info any?
  "
  create an Event"
  ([namespace string?, body v1beta1-event, ] (create-namespaced-event-with-http-info namespace body nil))
  ([namespace string?, body v1beta1-event, {:keys [pretty dryRun fieldManager]} (s/map-of keyword? any?)]
   (check-required-params namespace body)
   (call-api "/apis/events.k8s.io/v1beta1/namespaces/{namespace}/events" :post
             {:path-params   {"namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "dryRun" dryRun "fieldManager" fieldManager }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec create-namespaced-event v1beta1-event
  "
  create an Event"
  ([namespace string?, body v1beta1-event, ] (create-namespaced-event namespace body nil))
  ([namespace string?, body v1beta1-event, optional-params any?]
   (let [res (:data (create-namespaced-event-with-http-info namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1-event res st/string-transformer)
        res))))


(defn-spec delete-collection-namespaced-event-with-http-info any?
  "
  delete collection of Event"
  ([namespace string?, ] (delete-collection-namespaced-event-with-http-info namespace nil))
  ([namespace string?, {:keys [pretty allowWatchBookmarks continue dryRun fieldSelector gracePeriodSeconds labelSelector limit orphanDependents propagationPolicy resourceVersion timeoutSeconds watch body]} (s/map-of keyword? any?)]
   (check-required-params namespace)
   (call-api "/apis/events.k8s.io/v1beta1/namespaces/{namespace}/events" :delete
             {:path-params   {"namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "allowWatchBookmarks" allowWatchBookmarks "continue" continue "dryRun" dryRun "fieldSelector" fieldSelector "gracePeriodSeconds" gracePeriodSeconds "labelSelector" labelSelector "limit" limit "orphanDependents" orphanDependents "propagationPolicy" propagationPolicy "resourceVersion" resourceVersion "timeoutSeconds" timeoutSeconds "watch" watch }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec delete-collection-namespaced-event v1-status
  "
  delete collection of Event"
  ([namespace string?, ] (delete-collection-namespaced-event namespace nil))
  ([namespace string?, optional-params any?]
   (let [res (:data (delete-collection-namespaced-event-with-http-info namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1-status res st/string-transformer)
        res))))


(defn-spec delete-namespaced-event-with-http-info any?
  "
  delete an Event"
  ([name string?, namespace string?, ] (delete-namespaced-event-with-http-info name namespace nil))
  ([name string?, namespace string?, {:keys [pretty dryRun gracePeriodSeconds orphanDependents propagationPolicy body]} (s/map-of keyword? any?)]
   (check-required-params name namespace)
   (call-api "/apis/events.k8s.io/v1beta1/namespaces/{namespace}/events/{name}" :delete
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "dryRun" dryRun "gracePeriodSeconds" gracePeriodSeconds "orphanDependents" orphanDependents "propagationPolicy" propagationPolicy }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec delete-namespaced-event v1-status
  "
  delete an Event"
  ([name string?, namespace string?, ] (delete-namespaced-event name namespace nil))
  ([name string?, namespace string?, optional-params any?]
   (let [res (:data (delete-namespaced-event-with-http-info name namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1-status res st/string-transformer)
        res))))


(defn-spec get-api-resources-with-http-info any?
  "
  get available resources"
  []
  (call-api "/apis/events.k8s.io/v1beta1/" :get
            {:path-params   {}
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types []
             :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
             :auth-names    ["BearerToken"]}))

(defn-spec get-api-resources v1-api-resource-list
  "
  get available resources"
  []
  (let [res (:data (get-api-resources-with-http-info))]
    (if (:decode-models *api-context*)
       (st/decode v1-api-resource-list res st/string-transformer)
       res)))


(defn-spec list-event-for-all-namespaces-with-http-info any?
  "
  list or watch objects of kind Event"
  ([] (list-event-for-all-namespaces-with-http-info nil))
  ([{:keys [allowWatchBookmarks continue fieldSelector labelSelector limit pretty resourceVersion timeoutSeconds watch]} (s/map-of keyword? any?)]
   (call-api "/apis/events.k8s.io/v1beta1/events" :get
             {:path-params   {}
              :header-params {}
              :query-params  {"allowWatchBookmarks" allowWatchBookmarks "continue" continue "fieldSelector" fieldSelector "labelSelector" labelSelector "limit" limit "pretty" pretty "resourceVersion" resourceVersion "timeoutSeconds" timeoutSeconds "watch" watch }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf" "application/json;stream=watch" "application/vnd.kubernetes.protobuf;stream=watch"]
              :auth-names    ["BearerToken"]})))

(defn-spec list-event-for-all-namespaces v1beta1-event-list
  "
  list or watch objects of kind Event"
  ([] (list-event-for-all-namespaces nil))
  ([optional-params any?]
   (let [res (:data (list-event-for-all-namespaces-with-http-info optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1-event-list res st/string-transformer)
        res))))


(defn-spec list-namespaced-event-with-http-info any?
  "
  list or watch objects of kind Event"
  ([namespace string?, ] (list-namespaced-event-with-http-info namespace nil))
  ([namespace string?, {:keys [pretty allowWatchBookmarks continue fieldSelector labelSelector limit resourceVersion timeoutSeconds watch]} (s/map-of keyword? any?)]
   (check-required-params namespace)
   (call-api "/apis/events.k8s.io/v1beta1/namespaces/{namespace}/events" :get
             {:path-params   {"namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "allowWatchBookmarks" allowWatchBookmarks "continue" continue "fieldSelector" fieldSelector "labelSelector" labelSelector "limit" limit "resourceVersion" resourceVersion "timeoutSeconds" timeoutSeconds "watch" watch }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf" "application/json;stream=watch" "application/vnd.kubernetes.protobuf;stream=watch"]
              :auth-names    ["BearerToken"]})))

(defn-spec list-namespaced-event v1beta1-event-list
  "
  list or watch objects of kind Event"
  ([namespace string?, ] (list-namespaced-event namespace nil))
  ([namespace string?, optional-params any?]
   (let [res (:data (list-namespaced-event-with-http-info namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1-event-list res st/string-transformer)
        res))))


(defn-spec patch-namespaced-event-with-http-info any?
  "
  partially update the specified Event"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-event-with-http-info name namespace body nil))
  ([name string?, namespace string?, body any?, {:keys [pretty dryRun fieldManager force]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/events.k8s.io/v1beta1/namespaces/{namespace}/events/{name}" :patch
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "dryRun" dryRun "fieldManager" fieldManager "force" force }
              :form-params   {}
              :body-param    body
              :content-types ["application/json-patch+json" "application/merge-patch+json" "application/strategic-merge-patch+json" "application/apply-patch+yaml"]
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec patch-namespaced-event v1beta1-event
  "
  partially update the specified Event"
  ([name string?, namespace string?, body any?, ] (patch-namespaced-event name namespace body nil))
  ([name string?, namespace string?, body any?, optional-params any?]
   (let [res (:data (patch-namespaced-event-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1-event res st/string-transformer)
        res))))


(defn-spec read-namespaced-event-with-http-info any?
  "
  read the specified Event"
  ([name string?, namespace string?, ] (read-namespaced-event-with-http-info name namespace nil))
  ([name string?, namespace string?, {:keys [pretty exact export]} (s/map-of keyword? any?)]
   (check-required-params name namespace)
   (call-api "/apis/events.k8s.io/v1beta1/namespaces/{namespace}/events/{name}" :get
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "exact" exact "export" export }
              :form-params   {}
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec read-namespaced-event v1beta1-event
  "
  read the specified Event"
  ([name string?, namespace string?, ] (read-namespaced-event name namespace nil))
  ([name string?, namespace string?, optional-params any?]
   (let [res (:data (read-namespaced-event-with-http-info name namespace optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1-event res st/string-transformer)
        res))))


(defn-spec replace-namespaced-event-with-http-info any?
  "
  replace the specified Event"
  ([name string?, namespace string?, body v1beta1-event, ] (replace-namespaced-event-with-http-info name namespace body nil))
  ([name string?, namespace string?, body v1beta1-event, {:keys [pretty dryRun fieldManager]} (s/map-of keyword? any?)]
   (check-required-params name namespace body)
   (call-api "/apis/events.k8s.io/v1beta1/namespaces/{namespace}/events/{name}" :put
             {:path-params   {"name" name "namespace" namespace }
              :header-params {}
              :query-params  {"pretty" pretty "dryRun" dryRun "fieldManager" fieldManager }
              :form-params   {}
              :body-param    body
              :content-types []
              :accepts       ["application/json" "application/yaml" "application/vnd.kubernetes.protobuf"]
              :auth-names    ["BearerToken"]})))

(defn-spec replace-namespaced-event v1beta1-event
  "
  replace the specified Event"
  ([name string?, namespace string?, body v1beta1-event, ] (replace-namespaced-event name namespace body nil))
  ([name string?, namespace string?, body v1beta1-event, optional-params any?]
   (let [res (:data (replace-namespaced-event-with-http-info name namespace body optional-params))]
     (if (:decode-models *api-context*)
        (st/decode v1beta1-event res st/string-transformer)
        res))))


