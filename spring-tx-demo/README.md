
# test

```http request
### list all
GET http://localhost:8080/tests/list

### delete
DELETE http://localhost:8080/tests/1


### create
POST http://localhost:8080/tests
Content-Type: application/json

{
  "id": 1,
  "name": "fail",
  "address": "test1",
  "gender": "test1",
  "phoneNumber": "test1",
  "school": "test1"
}


```