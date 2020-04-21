describe('todo list', function () {
    let page;

    before (async function () {
      page = await browser.newPage();
      await page.goto('http://127.0.0.1:3000/');
    });
  
    after (async function () {
      await page.close();
    });

    it('should have correct title', async function() {
      expect(await page.title()).to.eql('Todo list');
  });

  describe('add todo', function () {

    it('should new todo correct', async function() {
      await page.waitFor('.task-input');
      await page.type('.task-input', 'new todo item', {delay: 50});
      await page.click('.container .add-button');
      let todoList = await page.waitFor('.task-items');
      const expectInputContent = await page.evaluate(todoList => todoList.lastChild.querySelector('textarea').textContent, todoList);
      expect(expectInputContent).to.eql('new todo item');
    }); 

  });

  describe('edit todo', function () {
      it('should update todo', async function() {      

          await page.click('.task-items .task-item:last-child .edit-button');
          const textareaElement=await page.$('.task-item:last-child textarea');
          await textareaElement.click( {clickCount: 3})
          await textareaElement.type('updated content');
          await page.$eval('.task-item:last-child textarea', textarea => textarea.blur());
          let theLastItem = await page.waitFor('.task-items .task-item:last-child');
          const expectInputContent = await page.evaluate(task => task.querySelector('textarea').textContent, theLastItem);
          expect(expectInputContent).to.eql('updated content');
        });
  });

  describe('delete todo', function () {
    it('should delete the new todo', async function(){
      const todoItem = await page.waitForSelector('.task-item:last-child');
      const deleteBtn = await todoItem.$('.delete-button');
      const item_id = await page.evaluate(todoItem => todoItem.getAttribute('.task-item'), todoItem);
  
      await deleteBtn.click();
      await page.waitFor(500);
      expect(item_id).to.eql(null);
      });
     
  });




  
  });