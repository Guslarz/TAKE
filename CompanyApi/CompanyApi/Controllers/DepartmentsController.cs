using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using CompanyApi.Models;

namespace CompanyApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class DepartmentsController : ControllerBase
    {
        private readonly CompanyDbContext _context;

        public DepartmentsController(CompanyDbContext context)
        {
            _context = context;
        }

        // GET: api/Departments
        [HttpGet]
        public async Task<ActionResult<IEnumerable<DepartmentDTO>>> GetDepartments()
        {
            if (_context.Departments == null)
            {
                return NotFound();
            }
            return await _context.Departments
                .Select(department => DepartmentToDTO(department))
                .ToListAsync();
        }

        // GET: api/Departments/5
        [HttpGet("{id}")]
        public async Task<ActionResult<DepartmentDTO>> GetDepartment(int id)
        {
            if (_context.Departments == null)
            {
                return NotFound();
            }
            var department = await _context.Departments
                .FindAsync(id);

            if (department == null)
            {
                return NotFound();
            }

            return DepartmentToDTO(department);
        }

        // PUT: api/Departments/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutDepartment(int id, DepartmentDTO dto)
        {
            if (id != dto.DepartmentId)
            {
                return BadRequest();
            }

            _context.Entry(DTOToDepartment(dto)).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!DepartmentExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/Departments
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Department>> PostDepartment(DepartmentDTO dto)
        {
            if (_context.Departments == null)
            {
                return Problem("Entity set 'CompanyDbContext.Departments'  is null.");
            }
            _context.Departments.Add(DTOToDepartment(dto));
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetDepartment", new { id = dto.DepartmentId }, dto);
        }

        // DELETE: api/Departments/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteDepartment(int id)
        {
            if (_context.Departments == null)
            {
                return NotFound();
            }
            var department = await _context.Departments.FindAsync(id);
            if (department == null)
            {
                return NotFound();
            }

            _context.Departments.Remove(department);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool DepartmentExists(int id)
        {
            return (_context.Departments?.Any(e => e.DepartmentId == id)).GetValueOrDefault();
        }

        private static DepartmentDTO DepartmentToDTO(Department department) =>
            new DepartmentDTO
            {
                DepartmentId = department.DepartmentId,
                Name = department.Name
            };

        private static Department DTOToDepartment(DepartmentDTO dto) =>
            new Department
            {
                DepartmentId = dto.DepartmentId,
                Name = dto.Name
            };
    }
}
