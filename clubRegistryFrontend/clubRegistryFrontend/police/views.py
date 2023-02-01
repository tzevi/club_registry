from django.views.generic import ListView
from .service import activate_shop, get_inactive_shops
from django.http import HttpResponse
from django.contrib import messages
from django.shortcuts import redirect
from django.core.exceptions import PermissionDenied

class ShopsView(ListView):
    template_name = 'shops.html'
    context_object_name = 'shops'

    def get_queryset(self):
        data =get_inactive_shops(self.request)
        if data['status_code']==200:
            return data['data']
        else:
            raise PermissionDenied("Not authenticated")

def ActivateShopView(request, id):
    club= activate_shop(id,request)
    if club['status_code']==200:
        messages.success(request,"Shop was activated successfully.")
        return redirect('police:shops')
    else:
        return HttpResponse(status=int(club['status_code']))
