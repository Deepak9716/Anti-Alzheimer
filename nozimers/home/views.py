from django.shortcuts import render
from .models import User_ID
# Create your views here.

def user(request):
    user_id = str(request.POST['user_id'])
    uid = User_ID.create(user_id)
    User_ID.objects.save(uid)

