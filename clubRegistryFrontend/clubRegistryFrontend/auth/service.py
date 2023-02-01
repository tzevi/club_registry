import httpx
from django.template.loader import get_template
import environ
import json
env = environ.Env()
from django.shortcuts import render


environ.Env.read_env()

url = env('HOST_URL')

auth_url =f'{url}/auth/'

def signin(user: dict):
    user=json.dumps(user)
    headers = {'Content-Type': 'application/json'}
    r= httpx.post(f'{auth_url}signin',data=user,headers=headers)
    if r.status_code== 200:
        response = {'status_code': r.status_code,'data':r.json()}
        return response
    else:
        response = {'status_code': r.status_code,'data':'Wrong username or password'}
        return response

def signup(user: dict):
    user=json.dumps(user)
    headers = {'Content-Type': 'application/json'}
    r= httpx.post(f'{auth_url}signup',data=user,headers=headers)
    if r.status_code== 200:
        print(r)
        response = {'status_code': r.status_code,'data':r.json()}
        return response
    else:
        response = {'status_code': r.status_code,'data':r.text}
        return response