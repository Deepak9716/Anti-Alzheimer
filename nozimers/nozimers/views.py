from __future__ import unicode_literals
from django.contrib.auth.decorators import login_required
from django.shortcuts import render
from django.http import HttpResponse
from . import detect_faces
import json
from . import getids
from PIL import Image
import base64
from io import BytesIO
import io
from time import sleep
from nozimers import send_message

def get_prediction(request):
    image = request.POST['image']
    #encode to byte
    image = image.encode()
    image = base64.decodebytes(image)
    
    filewrite = open("image.png", 'wb')
    filewrite.write(image)
    filewrite.close()
    filewrite = open("image.png", 'rb')
    try:
        profile = getids.predict_final_face(filewrite)
        profile['image'] = profile['image']
        print(json.dumps(profile))
        return HttpResponse(json.dumps(profile))
    except:
        return HttpResponse("error")
    

def save_profile(request):
    name = str(request.POST['name'])
    livesin = str(request.POST['livesIn'])
    age = str(request.POST['age'])
    placeofmeeting = str(request.POST['placeOfMeeting'])
    timeofmeeting = str(request.POST['timeOfMeeting'])
    relation = str(request.POST['relation'])
    notes = str(request.POST['notes'])
    image = request.POST['image']
    
    image = image.encode()
    image = base64.decodebytes(image)
    filewrite = open("nozimers/images/{}.png".format(name), 'wb')
    filewrite.write(image)
    filewrite.close()
    response = detect_faces.get_fid_and_pred(r"nozimers/images/{}.png".format(name))
    fid = json.loads(response)[0]['faceId']
    file = open("nozimers/people.json")
    data = json.loads(file.read())
    file.close()
    person = {
           'name':name,
           'livesIn': livesin,
           'age': age,
           'placeOfMeeting':placeofmeeting,
           'timeOfMeeting':timeofmeeting,
           'relation':relation,
           'notes':notes,
           "faceId": fid,
           "image": r"{}.png".format(name)
           }
    data["people"].append(person)
    print(data)
    with open("nozimers/people.json", 'w') as file:
        file.write(json.dumps(data))
    return HttpResponse("Done")

def user(request):
    user_id = str(request.POST['user_id'])
    api_url = ""


def broadcast(request):
    send_message.send_message("Your loved one may be wandering off. Please check his location", 8373984076)
    send_message.send_message("Your loved one may be wandering off. Please check his location", 9555653822)
    send_message.send_message("Your loved one may be wandering off. Please check his location", 9999621223)
    


    
    
