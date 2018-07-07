import detect_faces
import json
import http.client, urllib.request, urllib.parse, urllib.error, requests, json
from PIL import Image


def saveIds():
    file = open("people.json")
    data = json.loads(file.read())
    file.close()
    for person in data['people']:
        response = detect_faces.get_fid_and_pred(r"images/"+person['image'])
        response = json.loads(response)
        fid = response[0]['faceId']
        person['faceId'] = fid



def predict_face(image):
    subscription_key2 = 'e916c6e4db6443ac80fee053ea5d1f80'
    subscription_key = '87dda27634324d9e9d51f14841a8759b'
    uri_base = 'https://westcentralus.api.cognitive.microsoft.com/face/v1.0/findsimilars'
    response = detect_faces.get_fid_and_pred_from_PIL_image(image)
    response = json.loads(response)
    fid = response[0]['faceId']
    fids = []
    file = open("people.json")
    data = json.loads(json.loads(file.read()))
    file.close()
    for person in data['people']:
        fids.append(person['faceId'])
    headers = {
    'Content-Type': 'application/json',
    'Ocp-Apim-Subscription-Key': subscription_key,}
    params = {
    'faceId':str(fid),
    'faceIds':fids,
    'maxNumOfCandidatesReturned':1,
    }
    if True:
        # Execute the REST API call and get the response.
        response = requests.request('POST', uri_base,headers=headers, json=params, params=params)
        parsed = json.loads(response.text)
        return json.dumps(parsed, sort_keys=True)
    
    else:
        print("error")



def retrieve_details_from_id(fid):
    file = open("people.json")
    data = json.loads(json.loads(file.read()))
    file.close()
    for person in data['people']:
        print("fid = ", fid," people = ", person['faceId'])
        if fid == person['faceId']:
            return person

def predict_final_face(image):
    facedata = predict_face(image)
    fid = json.loads(facedata)[0]['faceId']
    profile = retrieve_details_from_id(fid)
    return profile


