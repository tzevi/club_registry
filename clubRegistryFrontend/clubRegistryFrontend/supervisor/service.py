import httpx
import environ
import json
import ast
from django.http import HttpRequest
env = environ.Env()


environ.Env.read_env()

url = env('HOST_URL')

clubs_url =f'{url}/clubs/'
shops_url =f'{url}/shops/'

def get_supervisor_active_club(request:HttpRequest):
    user = ast.literal_eval(request.COOKIES["user"])
    token = user['token']
    headers = {
        'Authorization': "Bearer " +token,
        'content-type': 'application/json',
        'active' : 'true',
        'supervisor':user['username']
    }
    r= httpx.get(f'{clubs_url}supervisor_active',headers=headers)
    if r.status_code== 200:
        if r.text =='':
            response = {'status_code': r.status_code,'data':None}
        else:
            response = {'status_code': r.status_code,'data':r.json()}
        return response
    else:
        response = {'status_code': r.status_code,'data':r.text}
        return response

def get_supervisor_inactive_club(request:HttpRequest):
    user = ast.literal_eval(request.COOKIES["user"])
    token = user['token']
    headers = {
        'Authorization': "Bearer " +token,
        'content-type': 'application/json',
        'active' : 'false',
        'supervisor':user['username']
    }
    r= httpx.get(f'{clubs_url}supervisor_active',headers=headers)
    if r.status_code== 200:
        if r.text =='':
            response = {'status_code': r.status_code,'data':None}
        else:
            response = {'status_code': r.status_code,'data':r.json()}
        return response
    else:
        response = {'status_code': r.status_code,'data':r.text}
        return response

def add_club(club: dict,request:HttpRequest):
    user_cookie = ast.literal_eval(request.COOKIES["user"])
    token = user_cookie['token']
    club['supervisorUsername']=user_cookie['username']
    club=json.dumps(club)
    headers = {
        'Authorization': "Bearer " +token,
        'content-type': 'application/json'        
    }
    r= httpx.post(f'{clubs_url}',data=club,headers=headers)
    if r.status_code== 200:
        print(r)
        response = {'status_code': r.status_code,'data':r.json()}
        return response
    else:
        response = {'status_code': r.status_code,'data':r.text}
        return response

def get_club_shops(taxNo: str,request:HttpRequest):
    user_cookie = ast.literal_eval(request.COOKIES["user"])
    token = user_cookie['token']
    print(taxNo)
    headers = {
        'Authorization': "Bearer " +token,
        'content-type': 'application/json',
    }
    r= httpx.get(f'{shops_url}all_by_club_tax_no?taxNo={taxNo}',headers=headers)
    if r.status_code== 200:
        response = {'status_code': r.status_code,'data':r.json()}
        return response
    else:
        response = {'status_code': r.status_code,'data':r.text}
        return response


def get_shop(id: int,request:HttpRequest):
    user_cookie = ast.literal_eval(request.COOKIES["user"])
    token = user_cookie['token']
    headers = {
        'Authorization': "Bearer " +token,
        'content-type': 'application/json',
    }
    print(headers)
    r= httpx.get(f'{shops_url}{id}',headers=headers)
    if r.status_code== 200:
        print(r)
        response = {'status_code': r.status_code,'data':r.json()}
        return response
    else:
        print(r)
        response = {'status_code': r.status_code,'data':r.text}
        return response

def edit_club(club: dict,request:HttpRequest):
    club=json.dumps(club)
    user_cookie = ast.literal_eval(request.COOKIES["user"])
    token = user_cookie['token']
    headers = {
        'Authorization': "Bearer " +token,
        'content-type': 'application/json'        
    }
    print(club)
    r= httpx.put(f'{clubs_url}update',data=club,headers=headers)
    if r.status_code== 200:
        print(r)
        response = {'status_code': r.status_code,'data':r.json()}
        return response
    else:
        response = {'status_code': r.status_code,'data':r.text}
        return response

def delete_club(taxNo: str,request:HttpRequest):
    user_cookie = ast.literal_eval(request.COOKIES["user"])
    token = user_cookie['token']
    headers = {
        'Authorization': "Bearer " +token,
        'content-type': 'application/json'        
    }
    r= httpx.delete(f'{clubs_url}{taxNo}',headers=headers)
    if r.status_code== 200:
        print(r)
        response = {'status_code': r.status_code,'data':'club deleted'}
        return response
    else:
        response = {'status_code': r.status_code,'data':r.text}
        return response

def add_shop(shop: dict,request:HttpRequest):
    user_cookie = ast.literal_eval(request.COOKIES["user"])
    token = user_cookie['token']
    shop=json.dumps(shop)
    headers = {
        'Authorization': "Bearer " +token,
        'content-type': 'application/json'        
    }
    r= httpx.post(f'{shops_url}',data=shop,headers=headers)
    if r.status_code== 200:
        print(r)
        response = {'status_code': r.status_code,'data':r.json()}
        return response
    else:
        response = {'status_code': r.status_code,'data':r.text}
        return response


def edit_shop(shop: dict,request:HttpRequest):
    shop=json.dumps(shop)
    user_cookie = ast.literal_eval(request.COOKIES["user"])
    token = user_cookie['token']
    headers = {
        'Authorization': "Bearer " +token,
        'content-type': 'application/json'        
    }
    print(shop)
    r= httpx.put(f'{shops_url}update',data=shop,headers=headers)
    if r.status_code== 200:
        print(r)
        response = {'status_code': r.status_code,'data':r.json()}
        return response
    else:
        response = {'status_code': r.status_code,'data':r.text}
        return response

def delete_shop(shop_id: int,request:HttpRequest):
    user_cookie = ast.literal_eval(request.COOKIES["user"])
    token = user_cookie['token']
    headers = {
        'Authorization': "Bearer " +token,
        'content-type': 'application/json'        
    }
    r= httpx.delete(f'{shops_url}{shop_id}',headers=headers)
    if r.status_code== 200:
        print(r)
        response = {'status_code': r.status_code,'data':'club deleted'}
        return response
    else:
        response = {'status_code': r.status_code,'data':r.text}
        return response