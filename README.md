# TestAuth

### Development tools: 
* IDE : IntelliJ Ultimate
* Web Server: Wildfly
* localhost to internet: pagekite.me
* api reference: https://apiexplorersandbox.openbankproject.com

### How to use:
**1. Ping Web Server:**
* Send Request to http://testrecing.pagekite.me/AuthTest1/hello
* Receive Response (Raw JSON): 

  {"message":"Say hello to everyone"}

**2. Initiate Authentication via OBP API:**
* Send request to http://testrecing.pagekite.me/AuthTest1/initiate
* Return JSON response with 2 variables
  * Status : Success or Failure
  * LoginURL : Authorization URL (with Request Token)
* Sample :

{"status":"Success","loginURL":"https://apisandbox.openbankproject.com/oauth/authorize?oauth_token=`<_Request Token Here_>`"}
