package racetrack

class RaceController
{
  def scaffold = Race

  def search = {
    flash.message="Search Results for: ${params.q}"
    def resultsMap = Race.search(params.q, params)
    render(view: 'list',
        model: [raceInstanceList:resultsMap.results,
        raceInstanceTotal:Race.countHits(params.q)])
  }
  /*def index()
  {
    render "Hello World"
  }

  def foo = {
    render "Bar"
  } */

}
