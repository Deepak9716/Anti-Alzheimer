from __future__ import unicode_literals
from django.contrib.auth.decorators import login_required
from django.shortcuts import render
from django.http import HttpResponse
import json
from . import getids
from PIL import Image
import base64
from io import BytesIO
import io


def get_prediction(request):
    im = BytesIO(base64.b64decode(request.POST['image'].encode()))
    print(type(im))
    profile = getids.predict_final_face(im)
    profile['image'] = '/images/'+profile['image']
    print(profile)
    return HttpResponse(profile)
    

