import httpx
import environ
import json
import ast
from django.http import HttpRequest
env = environ.Env()


environ.Env.read_env()

url = env('HOST_URL')

clubs_url =f'{url}/clubs/'


def get_inactive_clubs(request:HttpRequest):
    user = ast.literal_eval(request.COOKIES["user"])
    token = user['token']
    headers = {
        'Authorization': "Bearer " +token,
        'content-type': 'application/json',
    }
    r= httpx.get(f'{clubs_url}all_based_on_activity?active=false',headers=headers)
    if r.status_code== 200:
        if r.text =='':
            response = {'status_code': r.status_code,'data':None}
        else:
            response = {'status_code': r.status_code,'data':r.json()}
        return response
    else:
        response = {'status_code': r.status_code,'data':r.text}
        return response

def activate_club(taxNo:str,request:HttpRequest):
    user = ast.literal_eval(request.COOKIES["user"])
    token = user['token']
    headers = {
        'Authorization': "Bearer " +token,
        'content-type': 'application/json',
    }
    r= httpx.get(f'{clubs_url}find/{taxNo}',headers=headers)
    if r.status_code== 200:
        data=r.json()
        data['active']='true'
        data=json.dumps(data)
        new_r= httpx.put(f'{clubs_url}update',data=data,headers=headers)
        if new_r.status_code==200:
            response = {'status_code': new_r.status_code,'data':new_r.json()}
            return response
        else:
            response = {'status_code': new_r.status_code,'data':new_r.text}
            return response
    else:
        response = {'status_code': r.status_code,'data':r.text}
        return response