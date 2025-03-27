document.addEventListener('DOMContentLoaded', () => {
    const accordionToggles = document.querySelectorAll('.accordion-toggle');
    
    accordionToggles.forEach(toggle => {
      if (window.innerWidth < 640) {
        const content = toggle.nextElementSibling;
        toggle.classList.add('active');
        content.classList.add('active');
        content.style.maxHeight = content.scrollHeight + 'px';
      }
      
      toggle.addEventListener('click', function () {
        if (window.innerWidth >= 640) return;
        
        this.classList.toggle('active');
        let content = this.nextElementSibling;
        content.classList.toggle('active');
        
        content.style.maxHeight = content.classList.contains('active') 
          ? content.scrollHeight + 'px' 
          : '0';
      });
    });

    window.addEventListener('resize', () => {
      const isMobile = window.innerWidth < 640;
      accordionToggles.forEach(toggle => {
        let content = toggle.nextElementSibling;
        if (isMobile) {
          if (toggle.textContent.trim() === 'Enterprise') {
            toggle.classList.add('active');
            content.classList.add('active');
            content.style.maxHeight = content.scrollHeight + 'px';
          } else {
            toggle.classList.remove('active');
            content.classList.remove('active');
            content.style.maxHeight = '0';
          }
        } else {
          toggle.classList.remove('active');
          content.classList.add('active');
          content.style.maxHeight = '';
        }
      });
    });
  });