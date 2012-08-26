package racetrack

class AdminFilters {
     // sooo many auth plugins we could have used
    def filters = {
      // filter methods for admin only access via regex
        adminOnly(controller:'*', action:'(create|edit|update|delete|save)') {

            before = {
              if(!session?.user?.admin)
              {
                flash.message="Sorry, admin only"
                redirect(controller: "race", action:"list")
                return false
              }
            }

        }
    }
}
