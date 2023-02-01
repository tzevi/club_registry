from django.views.generic import FormView,DetailView
from .forms import *
from django.contrib.messages.views import SuccessMessageMixin
from .service import add_shop, delete_shop, edit_shop, get_club_shops, get_shop, get_supervisor_active_club,get_supervisor_inactive_club,add_club,delete_club,edit_club
from django.http import HttpResponse
from django.contrib import messages
from django.shortcuts import redirect

class ApplicationView(DetailView):
    template_name = 'application.html'
    context_object_name = 'application'

    def get_object(self):
        data =get_supervisor_inactive_club(self.request)
        if data['status_code']==200:
            return data['data']
        else:
            return HttpResponse(status=int(data['status_code']))


class ClubView(DetailView):
    template_name = 'club.html'
    context_object_name = 'club'

    def get_object(self):
        data =get_supervisor_active_club(self.request)
        print(data)
        if data['status_code']==200 and data['data'] != None:
            print("asasa")
            shops = get_club_shops(data['data']['taxNo'],self.request)
            data['data']['shops']=shops['data']
            return data['data']
        else:
            return {}

class AddApplicationView(SuccessMessageMixin,FormView):
    template_name = 'add_application.html'
    success_message = ("Application saved successfully")
    form_class = AddClubForm

    def form_valid(self, form) :
        data= add_club(form.cleaned_data,self.request)
        if data['status_code'] == 200:
            return super().form_valid(form)
        else:
            return self.render_to_response(self.get_context_data(form=form,message=data['data']))
    
    def get_success_url(self):
        return "/supervisor/application"

class EditClubView(SuccessMessageMixin,FormView):
    template_name = 'edit_club.html'
    success_message = ("Club updated successfully")
    form_class = EditClubForm

    def get(self, request, *args, **kwargs):
        club= get_supervisor_active_club(self.request)
        if club['status_code']==200:
            form = EditClubForm(initial=club['data'])
        
            return self.render_to_response(self.get_context_data(
                form=form, user=club['data']))
        else:
            return HttpResponse(status=int(club['status_code']))

        

    def form_valid(self, form) :
        data= edit_club(form.cleaned_data,self.request)
        if data['status_code'] == 200:
            return super().form_valid(form)
        else:
            return self.render_to_response(self.get_context_data(form=form,message=data['data']))
    
    def get_success_url(self):
        return "/supervisor/club"


def DeleteClubView(request, taxNo):
    data= delete_club(taxNo,request)
    if data['status_code']==200:
        messages.success(request,"Club was deleted successfully.")
        return redirect('supervisor:application')
    else:
        return HttpResponse(status=int(data['status_code']))


class AddShopView(SuccessMessageMixin,FormView):
    template_name = 'add_shop.html'
    success_message = ("Shop saved successfully")
    form_class = AddShopForm

    def form_valid(self, form) :
        shop=form.cleaned_data
        shop['clubTaxNo'] = self.kwargs['taxNo']
        data= add_shop(shop,self.request)
        if data['status_code'] == 200:
            return super().form_valid(form)
        else:
            return self.render_to_response(self.get_context_data(form=form,message=data['data']))
    
    def get_success_url(self):
        return "/supervisor/club"

class EditShopView(SuccessMessageMixin,FormView):
    template_name = 'edit_shop.html'
    success_message = ("Shop updated successfully")
    form_class = EditShopForm

    def get(self, request, *args, **kwargs):
        shop= get_shop(self.kwargs['id'],self.request)
        if shop['status_code']==200:
            form = EditShopForm(initial=shop['data'])
            print(shop['data'])
            return self.render_to_response(self.get_context_data(
                form=form, user=shop['data']))
        else:
            return HttpResponse(status=int(shop['status_code']))

    def form_valid(self, form) :
        print("valid")
        data= edit_shop(form.cleaned_data,self.request)
        if data['status_code'] == 200:
            return super().form_valid(form)
        else:
            return self.render_to_response(self.get_context_data(form=form,message=data['data']))
    
    def get_success_url(self):
        return "/supervisor/club"


def DeleteShopView(request, taxNo,id):
    data= delete_shop(id,request)
    if data['status_code']==200:
        messages.success(request,"Shop was deleted successfully.")
        return redirect('supervisor:club')
    else:
        return HttpResponse(status=int(data['status_code']))