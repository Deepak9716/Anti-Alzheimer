import requests
import json
from get_home import Home
from .models import User_ID, Location

from django_cron import CronJobBase, Schedule

class get_home(CronJobBase):
    RUN_AT_MINS = ['23:00', '05:00']
    schedule = Schedule(run_at_times=RUN_AT_TIMES)
    code = 'home'

    user_id = User_ID.objects.all[-1]
    last_location = Location.objects.all()[-1]

    def do(self):
        address = Home(user_id)
        if not last_location:
            loc = Location.create(user_id, address)
            loc.save()
        else if address == last_location.address:
            print(address)
            last_location.final = True
            last_location.save()
        else:
            last_location.address = None
            last_location.save()
