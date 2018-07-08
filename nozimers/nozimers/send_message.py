from twilio.rest import Client


def send_message(message, recipient):
    # Your Account SID from twilio.com/console
    account_sid = "ACa8f642fe028873ff9e883697fa95875c"
    # Your Auth Token from twilio.com/console
    auth_token  = "162af665d53e0f88e8fef730dc568b80"
    client = Client(account_sid, auth_token)
    message = client.messages.create(
        to="+91{}".format(recipient),
        from_="+12133443879",
        body=message)
    print(message.sid)
