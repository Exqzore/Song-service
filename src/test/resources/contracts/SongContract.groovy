package contracts

import org.springframework.cloud.contract.spec.Contract

[Contract.make {
    request {
        url "/songs/1"
        method GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body("id": 1,
                "name": "name",
                "artist": "artist",
                "album": "album",
                "length": "1",
                "resourceId": 1,
                "year": 2022)
    }
},
 Contract.make {
     request {
         url "/songs"
         method POST()
         headers {
             contentType applicationJson()
         }
         body("name": "name",
                 "artist": "artist",
                 "album": "album",
                 "length": "1",
                 "resourceId": 1,
                 "year": 2022)
     }

     response {
         status OK()
         headers {
             contentType applicationJson()
         }
         body("id": anyNumber())
     }
 },
 Contract.make {
     request {
         url "/songs/1"
         method DELETE()
     }

     response {
         status OK()
         headers {
             contentType applicationJson()
         }
         body("ids": 1)
     }
 }

]