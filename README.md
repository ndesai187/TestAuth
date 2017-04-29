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

{"status":"Success","loginURL":"https://apisandbox.openbankproject.com/oauth/authorize?oauth_token=<_Request Token Here_>"}

**3. Logging in and CallBack**
* Once you go to loginURL (from step 2), you are in OBP Sandbox domain and out of our reach :wave:
* Enter your user name and password to authorize our app :thumbsup:
* This will redirect you to our callBack URL with access token and Verifier.
Sample:

http://testrecing.pagekite.me/AuthTest1/callBack?oauth_token=<Access_Token_here>&oauth_verifier=<verifier_here>



