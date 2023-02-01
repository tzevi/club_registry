from django.views.generic import ListView
from .service import get_inactive_clubs,activate_club
from django.http import HttpResponse
from django.contrib import messages
from django.shortcuts import redirect
from django.core.exceptions import PermissionDenied

class ClubsView(ListView):
    template_name = 'clubs.html'
    context_object_name = 'clubs'

    def get_queryset(self):
        data =get_inactive_clubs(self.request)
        if data['status_code']==200:
            return data['data']
        else:
            raise PermissionDenied("Not authenticated")



def ActivateClubView(request, taxNo):
    club= activate_club(taxNo,request)
    if club['status_code']==200:
        messages.success(request,"Application was activated successfully.")
        return redirect('gga:applications')
    else:
        return HttpResponse(status=int(club['status_code']))
