import http.client, urllib.request, urllib.parse, urllib.error, requests, json
from PIL import Image

###############################################
#### Update or verify the following values. ###
###############################################

# Replace the subscription_key string value with your valid subscription key.
subscription_key2 = 'e916c6e4db6443ac80fee053ea5d1f80'
subscription_key = '87dda27634324d9e9d51f14841a8759b'

# Replace or verify the region.
#
# You must use the same region in your REST API call as you used to obtain your subscription keys.
# For example, if you obtained your subscription keys from the westus region, replace 
# "westcentralus" in the URI below with "westus".
#
# NOTE: Free trial subscription keys are generated in the westcentralus region, so if you are using
# a free trial subscription key, you should not need to change this region.
uri_base = 'https://westcentralus.api.cognitive.microsoft.com'

# Request headers.
headers = {
    'Content-Type': 'application/octet-stream',
    'Ocp-Apim-Subscription-Key': subscription_key,
}

# Request parameters.
params = {
    'returnFaceId': 'true',
    'returnFaceLandmarks': 'false',
    'returnFaceAttributes': 'gender',
}


def get_fid_and_pred(imagepath):
    # Body. The URL of a JPEG image to analyze.
   # body = {'url': 'https://upload.wikimedia.org/wikipedia/commons/c/c3/RH_Louise_Lillian_Gish.jpg'}
    try:
        # Execute the REST API call and get the response.
        response = requests.request('POST', uri_base + '/face/v1.0/detect',headers=headers, json=None, data=open(imagepath, 'rb'), params=params)
        parsed = json.loads(response.text)
        return json.dumps(parsed, sort_keys=True)
    
    except Exception as e:
        print('Error:')
        print(e)

def get_fid_and_pred_from_PIL_image(image):
    # Body. The URL of a JPEG image to analyze.
   # body = {'url': 'https://upload.wikimedia.org/wikipedia/commons/c/c3/RH_Louise_Lillian_Gish.jpg'}
    try:
        # Execute the REST API call and get the response.
        response = requests.request('POST', uri_base + '/face/v1.0/detect',headers=headers, json=None, data = image, params=params)
        parsed = json.loads(response.text)
        return json.dumps(parsed, sort_keys=True)
    
    except Exception as e:
        print('Error:')
        print(e)

####################################
