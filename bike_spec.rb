require 'headless'
require 'selenium-webdriver'
require 'minitest/autorun'

website_url = ARGV[0]

describe 'Bike' do
  before do
    @headless = Headless.new
    @headless.start
    @driver = Selenium::WebDriver.for :firefox
    @driver.navigate.to "#{website_url}/bike-shop"
    @driver.manage.timeouts.implicit_wait = 30
  end

  after do
    @headless.destroy
  end

  describe 'when site is available' do
     it 'Should show hello world' do
       h1 = @driver.find_element(:tag_name, 'H1').text
       puts h1
       assert h1 == 'Hello World!'
     end
  end
end
