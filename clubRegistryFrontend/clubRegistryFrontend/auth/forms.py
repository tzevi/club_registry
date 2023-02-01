from django import forms


ROLE_CHOICES = [
    ('ROLE_GGA', 'GGA'),
    ('ROLE_ADMIN', 'Admin'),
    ('ROLE_CLUB_SUPERVISOR', 'Club Supervisor'),
    ('ROLE_POLICE', 'Police'),

]
class SigninForm(forms.Form):
    username=forms.CharField(required=True)
    password=forms.CharField(required=True,widget=forms.PasswordInput)

class SignupForm(forms.Form):
    username=forms.CharField(required=True,min_length=4,max_length=25)
    email = forms.EmailField(required=True)
    password=forms.CharField(required=True,widget=forms.PasswordInput)
    confirm_password = forms.CharField(required=True,widget=forms.PasswordInput)
    first_name = forms.CharField(required=True,min_length=5)
    last_name = forms.CharField(required=True,min_length=5)
    userRole = forms.ChoiceField(choices=ROLE_CHOICES,label="Role",required=True)

    def clean(self):
        password = self.cleaned_data['password']
        confirm_password = self.cleaned_data['confirm_password']

        if password != confirm_password:
            self.add_error("confirm_password", "Passwords not match !")

        return self.cleaned_data
