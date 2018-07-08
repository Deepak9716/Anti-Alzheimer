from django.db import models

# Create your models here.
class User_ID(models.Model):
    user_id = models.CharField(max_length=256)

class Location(models.Model):
    user_id = models.ForeignKey(User_ID, on_delete=models.CASCADE)
    address = models.CharField(max_length=256, default=None)
    final = models.BooleanField(default=False)