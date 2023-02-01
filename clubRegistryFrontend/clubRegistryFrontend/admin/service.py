import httpx
import environ
import json
import ast
from django.http import HttpRequest
env = environ.Env()


environ.Env.read_env()

url = env('HOST_URL')

users_url =f'{url}/users/'

def get_users(request:HttpRequest):
    user = ast.literal_eval(request.COOKIES["user"])
    token = user['token']
    headers = {
        'Authorization': "Bearer " +token,
        'content-type': 'application/json'        
    }
    r= httpx.get(f'{users_url}all',headers=headers)
    if r.status_code== 200:
        response = {'status_code': r.status_code,'data':r.json()}
        return response
    else:
        response = {'status_code': r.status_code,'data':r.text}
        return response

def add_user(user: dict,request:HttpRequest):
    user=json.dumps(user)
    user_cookie = ast.literal_eval(request.COOKIES["user"])
    token = user_cookie['token']
    print(token)
    print(type(token))
    headers = {
        'Authorization': "Bearer " +token,
        'content-type': 'application/json'        
    }
    r= httpx.post(f'{users_url}',data=user,headers=headers)
    if r.status_code== 200:
        print(r)
        response = {'status_code': r.status_code,'data':r.json()}
        return response
    else:
        response = {'status_code': r.status_code,'data':r.text}
        return response


def get_user(username: str,request:HttpRequest):
    user_cookie = ast.literal_eval(request.COOKIES["user"])
    token = user_cookie['token']
    print(token)
    headers = {
        'accept': '*/*',
        'Authorization': "Bearer " +token,
        'username':username       
    }
    r= httpx.get(f'{users_url}user',headers=headers)
    if r.status_code== 200:
        print(r)
        response = {'status_code': r.status_code,'data':r.json()}
        return response
    else:
        print(r)
        response = {'status_code': r.status_code,'data':r.text}
        return response

def edit_user(user: dict,request:HttpRequest):
    user=json.dumps(user)
    user_cookie = ast.literal_eval(request.COOKIES["user"])
    token = user_cookie['token']
    headers = {
        'Authorization': "Bearer " +token,
        'content-type': 'application/json'        
    }
    r= httpx.put(f'{users_url}updateuser',data=user,headers=headers)
    if r.status_code== 200:
        print(r)
        response = {'status_code': r.status_code,'data':r.json()}
        return response
    else:
        response = {'status_code': r.status_code,'data':r.text}
        return response

def delete_user(username: str,request:HttpRequest):
    user_cookie = ast.literal_eval(request.COOKIES["user"])
    token = user_cookie['token']
    headers = {
        'Authorization': "Bearer " +token,
        'content-type': 'application/json'        
    }
    r= httpx.delete(f'{users_url}{username}',headers=headers)
    if r.status_code== 200:
        print(r)
        response = {'status_code': r.status_code,'data':'user deleted'}
        return response
    else:
        response = {'status_code': r.status_code,'data':r.text}
        return response