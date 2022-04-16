mvn clean compile

mvn clean package 

mvn spring-boot:run

# Add Product
curl --request POST \
    --header 'accept: application/json' \
    --header 'content-type: application/json' \
    --url http://localhost:8080/product/add \
    --data '{"id":"","productname":"Product Name 1","description":"Product Description 1","price":"99.99","created":""}'


# Get Product
curl --request GET \
    --url http://localhost:8080/product/get/e9b6c02d-0604-4bab-a3ea-6a7984654631

# Update Product
curl --request PATCH \
    --header 'content-type: application/json' \
    --url http://localhost:8080/product/update \
    --data '{"id":"b34d19cb-e284-4a58-bb76-b4fca89cf475","productname":"Product Name 2","description":"Product Description 2","price":"123.88","created":""}'


# Delete Product
curl --request DELETE \
    --url http://localhost:8080/product/delete/3e6e0dbe-a8aa-448c-9244-5e7b3330f539



curl --request PATCH \
    --url https://${ASTRA_DB_ID}-${ASTRA_DB_REGION}.apps.astra.datastax.com/api/rest/v2/keyspaces/${ASTRA_DB_KEYSPACE}/rest_example_products/e9b6c02d-0604-4bab-a3ea-6a7984654631 \
    --header 'content-type: application/json' \
    --header "x-cassandra-token: ${ASTRA_DB_APPLICATION_TOKEN}" \
    --data '{"productname":"Heavy Lift Arms 22","description":"Heavy lift arms capable of lifting 1,250 lbs of weight per arm. Sold as a set.","price":"4199.99","created":"2019-01-10T09:48:31.020Z"}' 

curl --request PATCH \
    --url https://e9cd0659-e8b4-4380-a94a-c8a063de9a38-asia-south1.apps.astra.datastax.com/api/rest/v2/keyspaces/sample/rest_example_products/c249b0ac-9e50-433c-81e0-ce2ab4edaade \
    --header 'content-type: application/json' \
    --header "x-cassandra-token: ${ASTRA_DB_APPLICATION_TOKEN}" \
    --data '{"productname":"Heavy Lift Arms 22","description":"Heavy lift arms capable of lifting 1,250 lbs of weight per arm. Sold as a set.","price":"4199.99"}' 