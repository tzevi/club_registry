from django.views.generic import FormView,ListView,DeleteView
from .forms import *
from django.contrib.messages.views import SuccessMessageMixin
from .service import delete_user, edit_user, get_users,add_user,get_user
from django.http import HttpResponse
from django.contrib import messages
from django.shortcuts import redirect
from django.core.exceptions import PermissionDenied

class UsersView(ListView):
    template_name = 'users.html'
    context_object_name = 'users'

    def get_queryset(self):
        data =get_users(self.request)
        if data['status_code']==200:
            return data['data']
        else:
            raise PermissionDenied("Not authenticated")


class AddUserView(SuccessMessageMixin,FormView):
    template_name = 'add_user.html'
    success_message = ("User added successfully")
    form_class = AddUserForm

    def form_valid(self, form) :
        data= add_user(form.cleaned_data,self.request)
        if data['status_code'] == 200:
            return super().form_valid(form)
        else:
            return self.render_to_response(self.get_context_data(form=form,message=data['data']))
    
    def get_success_url(self):
        return "/admin/users"

class EditUserView(SuccessMessageMixin,FormView):
    template_name = 'edit_user.html'
    success_message = ("User updated successfully")
    form_class = EditUserForm

    def get(self, request, *args, **kwargs):
        username = self.kwargs['username']
        user= get_user(username,self.request)
        if user['status_code']==200:
            form = EditUserForm(initial=user['data'])
        
            return self.render_to_response(self.get_context_data(
                form=form, user=user['data']))
        else:
            return HttpResponse(status=int(user['status_code']))

        

    def form_valid(self, form) :
        data= edit_user(form.cleaned_data,self.request)
        if data['status_code'] == 200:
            return super().form_valid(form)
        else:
            return self.render_to_response(self.get_context_data(form=form,message=data['data']))
    
    def get_success_url(self):
        return "/admin/users"


def DeleteUserView(request, username):
    user= delete_user(username,request)
    if user['status_code']==200:
        messages.success(request,"User was deleted successfully.")
        return redirect('admin:users')
    else:
        return HttpResponse(status=int(user['status_code']))