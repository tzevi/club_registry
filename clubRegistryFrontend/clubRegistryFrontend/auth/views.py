from django.views.generic import FormView
from .forms import *
from django.contrib.messages.views import SuccessMessageMixin
from .service import signin,signup
from django.http import HttpResponse,HttpRequest
from django.template.loader import get_template
from django.contrib import messages
from django.shortcuts import redirect

class SigninView(SuccessMessageMixin,FormView):
    template_name = 'login.html'
    success_message = ("You have successfully logged in.")
    form_class = SigninForm

    def form_valid(self, form) :
        data= signin(form.cleaned_data)
        if data['status_code'] == 200:
            user_role= data['data']['role']
            redirect_str="admin:users"
            if user_role == "ROLE_CLUB_SUPERVISOR":
                redirect_str="supervisor:club"
            elif user_role == "ROLE_GGA":
                redirect_str="gga:applications"
            elif user_role == "ROLE_POLICE":
                redirect_str="police:shops"
            html= redirect(redirect_str)
            html.set_cookie('user',data['data'])
            return html
        else:
            return self.render_to_response(self.get_context_data(form=form,message=data['data']))

class SignupView(SuccessMessageMixin,FormView):
    template_name = 'signup.html'
    success_message = ("You have successfully registered.")
    form_class = SignupForm

    def form_valid(self, form) :
        data= signup(form.cleaned_data)
        if data['status_code'] == 200:
            return super().form_valid(form)
        else:
            return self.render_to_response(self.get_context_data(form=form,message=data['data']))
    
    def get_success_url(self):
        return "/"


def LogoutView(request:HttpRequest):
    html= redirect('auth:login')
    html.delete_cookie('user')
    messages.success(request,"You have successfully logeed out.")
    return html