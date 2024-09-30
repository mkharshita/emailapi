# Curl:

**curl --location 'http://localhost:8080/send-email' \
--header 'Content-Type: application/json' \
--data-raw '{
"to":"mail@gmail.com",
"subject":"Testing",
"message": "God is great!"
}'**