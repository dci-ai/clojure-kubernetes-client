(ns kubernetes.specs.v1alpha1/cluster-role-binding
  (:require [clojure.spec.alpha :as s]
            [spec-tools.data-spec :as ds]
            [kubernetes.specs.v1/object-meta :refer :all]
            [kubernetes.specs.v1alpha1/role-ref :refer :all]
            [kubernetes.specs.v1alpha1/subject :refer :all]
            )
  (:import (java.io File)))


(def v1alpha1/cluster-role-binding-data
  {
   (ds/opt :apiVersion) string?
   (ds/opt :kind) string?
   (ds/opt :metadata) v1/object-meta-spec
   (ds/req :roleRef) v1alpha1/role-ref-spec
   (ds/req :subjects) (s/coll-of v1alpha1/subject-spec)
   })

(def v1alpha1/cluster-role-binding-spec
  (ds/spec
    {:name ::v1alpha1/cluster-role-binding
     :spec v1alpha1/cluster-role-binding-data}))
