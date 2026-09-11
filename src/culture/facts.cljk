(ns culture.facts
  "Country-level regional-culture catalog for Jamaica (JAM) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  the `marketentry.facts` / `statute.facts` catalogs of the iso3166
  siblings (ADR-2607141700); city-level counterparts live in the
  cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"JAM"
   [{:culture/id "jam.dish.jerk"
     :culture/name "Jerk"
     :culture/country "JAM"
     :culture/kind :dish
     :culture/summary "Style of cooking native to Jamaica in which meat is dry-rubbed or wet-marinated with Jamaican jerk spice, originating with the island's indigenous peoples and refined by Jamaican Maroons."
     :culture/url "https://en.wikipedia.org/wiki/Jerk_(cooking)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "jam.dish.ackee-and-saltfish"
     :culture/name "Ackee and saltfish"
     :culture/country "JAM"
     :culture/kind :dish
     :culture/summary "Jamaica's national dish of sautéed ackee fruit with salted codfish, onions, peppers, tomatoes and spices, typically served at breakfast."
     :culture/url "https://en.wikipedia.org/wiki/Ackee_and_saltfish"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "jam.dish.jamaican-patty"
     :culture/name "Jamaican patty"
     :culture/country "JAM"
     :culture/kind :dish
     :culture/summary "Semicircular Jamaican pastry with various fillings and spices baked inside a flaky shell, traditionally filled with seasoned ground meat."
     :culture/url "https://en.wikipedia.org/wiki/Jamaican_patty"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "jam.dish.rice-and-peas"
     :culture/name "Rice and peas"
     :culture/country "JAM"
     :culture/kind :dish
     :culture/summary "Traditional Caribbean side dish of legumes and rice, often with coconut milk; the name 'rice and peas' was originally used by Jamaicans, and the diaspora spread the dish worldwide."
     :culture/url "https://en.wikipedia.org/wiki/Rice_and_peas"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "jam.product.blue-mountain-coffee"
     :culture/name "Jamaican Blue Mountain Coffee"
     :culture/country "JAM"
     :culture/kind :product
     :culture/summary "Coffee grown in the Blue Mountains of Jamaica under a globally protected certification mark and Jamaican geographical indication; over 80% of exports go to Japan."
     :culture/url "https://en.wikipedia.org/wiki/Jamaican_Blue_Mountain_Coffee"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "jam.beverage.j-wray-and-nephew"
     :culture/name "J. Wray & Nephew rum"
     :culture/country "JAM"
     :culture/kind :beverage
     :culture/summary "Rum from J. Wray & Nephew Ltd., a distiller, blender and bottler established in 1825 in Kingston, Jamaica's largest spirit producer and owner of Appleton Estate, the island's oldest continuously operating distillery."
     :culture/url "https://en.wikipedia.org/wiki/J._Wray_%26_Nephew_Ltd."
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "jam.festival.reggae-sumfest"
     :culture/name "Reggae Sumfest"
     :culture/country "JAM"
     :culture/kind :festival
     :culture/summary "Largest music festival in Jamaica and the Caribbean, held annually in mid-July in Montego Bay since 1993."
     :culture/url "https://en.wikipedia.org/wiki/Reggae_Sumfest"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "jam.heritage.blue-and-john-crow-mountains"
     :culture/name "Blue and John Crow Mountains"
     :culture/country "JAM"
     :culture/kind :heritage
     :culture/summary "National park covering 495.2 km² (4.5% of Jamaica's land surface), recognized for its biodiversity; a UNESCO World Heritage Site under mixed cultural and natural criteria since 2015."
     :culture/url "https://en.wikipedia.org/wiki/Blue_and_John_Crow_Mountains"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-jam culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "JAM"))
                 " JAM entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
