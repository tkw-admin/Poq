# Missguided App - API Tests

## Overview

We used the iOS app to investigate the APIs mentioned in the tasks and wrote down our thoughts while doing this. 

* `Register` endpoint - account/register/{{CountryCode}}/{{UUID}}
   * Why do we have isPromotion twice in the request body?
   * There’s no step to confirm the account, which could lead to fake account being created
   * encryptedPassword field is not encrypted. Not sure why this info needs to be sent with the payload since we send the password in the credentials - password field
   * The username field should be an email address, but the server response is not very user friendly “400 - An error occurred while processing your request.”
   


* `Signin` endpoint - account/login/{{CountryCode}}/{{$UUID}}
   * Once the user is logged in:
      * It requests the checkout details
      * It requests the account details
   * isMastercard field is not required for signing in, thus I left it out of the scope of the tests. To be honest we don’t understand why this field is present in the login form.
   

* `Logout` - we found no specific endpoint for this action
    * When the user hits the logout button, 
       * GET - /ContentBlocks/{{CountryCode}}/1  with no authentication is triggered. 
       * DELETE - /wishlist/{{CountryCode}}/{{UUID}} - his / her wishlist is empty (not sure why, as I would expect to have the items I liked in my account once I login) 
       * POST - recommendations/apps/{{CountryCode}}/products/_recently_viewed if specific products were visited, a call with the recently visited products is triggered
       


* `Product Details` endpoint - /products/detail/{{CountryCode}}/{{ProductId}}?externalId={{ExternalProductID}}&poqUserId={{UUID}}
   * It requires productId and externalId, which can be found in the response to the call /products/by_external_ids/{{CountryCode}}
   



Other notes we took during the investigation:

* We couldn’t find a way to reset the password for the registered accounts. 
* Status Code - 429 - Rate limit exceeded would be interesting to have some tests around this limit

## Deliverables

* POQ.postman_collection.json
   * the Postman collection with the calls needed for the tests and the preset collection variables
   * the asserts cover the status code and the returned message
* TestData_API - Sign In.csv - the data file for testing the Signin endpoint, including the expected status code and message
* TestData_API - Register.csv - the data file for testing the Register endpoint, including the expected status code and message

Since there was no specific logout endpoint (we didn’t consider the CodeBlocks call a logout call), no tests were designed for the calls that are triggered after the logout - we considered these to be subsequent calls and not the actual logout calls
