﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using MvcNews.Data;
using MvcNews.Models;

namespace MvcNews.Controllers
{
    public class NewsController : Controller
    {
        private readonly NewsDbContext _context;

        public NewsController(NewsDbContext context)
        {
            _context = context;
        }

        // GET: News
        public async Task<IActionResult> Index()
        {
            return _context.News != null ?
                        View(await _context.News.ToListAsync()) :
                        Problem("Entity set 'NewsDbContext.News'  is null.");
        }

        // GET: News/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null || _context.News == null)
            {
                return NotFound();
            }

            var newsItem = await _context.News
                .FirstOrDefaultAsync(m => m.Id == id);
            if (newsItem == null)
            {
                return NotFound();
            }

            return View(newsItem);
        }

        // GET: News/Create
        public IActionResult Create()
        {
            var item = new NewsItem { Timestamp = System.DateTime.Now };
            return View(item);
        }

        // POST: News/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("Id,RowVersion,Timestamp,Text")] NewsItem newsItem)
        {
            if (ModelState.IsValid)
            {
                _context.Add(newsItem);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(newsItem);
        }

        // GET: News/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null || _context.News == null)
            {
                return NotFound();
            }

            var newsItem = await _context.News.FindAsync(id);
            if (newsItem == null)
            {
                return NotFound();
            }
            return View(newsItem);
        }

        // POST: News/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("Id,RowVersion,Timestamp,Text")] NewsItem newsItem)
        {
            if (id != newsItem.Id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(newsItem);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException e)
                {
                    if (!NewsItemExists(newsItem.Id))
                    {
                        return NotFound();
                    }
                    else
                    {
                        var entry = e.Entries.Single();
                        var databaseEntry = entry.GetDatabaseValues();
                        var databaseEntity = (NewsItem)databaseEntry.ToObject();

                        newsItem.RowVersion = (byte[])databaseEntity.RowVersion;
                        ModelState.Remove("RowVersion");

                        ModelState.AddModelError("Timestamp", "Current value: " + (DateTime)databaseEntity.Timestamp);
                        ModelState.AddModelError("Text", "Current value: " +
                         (string)databaseEntity.Text);


                        ModelState.AddModelError("", "Edytowane dane zostały zmodyfikowane lub usunięte przez innego użytkownika");
                        return View(newsItem);
                    }
                }
                return RedirectToAction(nameof(Index));
            }
            return View(newsItem);
        }

        // GET: News/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null || _context.News == null)
            {
                return NotFound();
            }

            var newsItem = await _context.News
                .FirstOrDefaultAsync(m => m.Id == id);
            if (newsItem == null)
            {
                return NotFound();
            }

            return View(newsItem);
        }

        // POST: News/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id, NewsItem newsItem)
        {
            if (_context.News == null)
            {
                return Problem("Entity set 'NewsDbContext.News'  is null.");
            }
            try
            {
                _context.News.Remove(newsItem);
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException e)
            {
                if (!NewsItemExists(newsItem.Id))
                {
                    return NotFound();
                }
                else
                {
                    ModelState.AddModelError("", "Unable to save changes. The record " +
                        "was modified by another user after you got the original value");

                    var entry = e.Entries.Single();
                    var databaseEntry = entry.GetDatabaseValues();
                    var databaseEntity = (NewsItem)databaseEntry.ToObject();
                    ModelState.Remove("RowVersion");
                    return View(databaseEntity);
                }
            }
            return RedirectToAction(nameof(Index));
        }

        private bool NewsItemExists(int id)
        {
            return (_context.News?.Any(e => e.Id == id)).GetValueOrDefault();
        }
    }
}
